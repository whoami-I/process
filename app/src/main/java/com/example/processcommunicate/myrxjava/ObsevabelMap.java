package com.example.processcommunicate.myrxjava;

public class ObsevabelMap<T, V> extends Observable<V> {

    private Function<T, V> f;
    private ObservableSource<T> source;

    public ObsevabelMap(ObservableSource<T> source, Function<T, V> f) {
        this.source = source;
        this.f = f;
    }

    //把Observer封装传递给上一级
    @Override
    public void subscribe(Observer<V> observer) {
        source.subscribe(new MapObserver(observer, f));

    }


    class MapObserver<T, V> implements Observer<T> {
        private Observer<V> observer;
        private Function<T, V> f;

        MapObserver(Observer<V> actual, Function<T, V> f) {
            this.observer = actual;
            this.f = f;
        }

        @Override
        public void onSubscribe() {

        }
        //将上层的事件变换一下，将变换后的事件传递给下一级
        @Override
        public void onNext(T t) {
            V v = f.apply(t);
            observer.onNext(v);
        }

        @Override
        public void onError() {

        }

        @Override
        public void onComplete() {

        }
    }
}
