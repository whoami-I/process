package com.example.processcommunicate.myrxjava;

public class ObservableCreate<T> extends Observable<T> {

    private ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    public void subscribe(Observer<T> observer) {

        Emitter<T> emitter = new CreateEmitter<>(observer);
        //先调用observer.onSubscribe
        observer.onSubscribe();
        //再调用source的subscribe方法
        source.subscribe(emitter);

    }

    class CreateEmitter<T> implements Emitter<T> {
        Observer<T> observer;

        CreateEmitter(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(T value) {
            observer.onNext(value);
        }

        @Override
        public void onError(Throwable error) {

        }

        @Override
        public void onComplete() {

        }
    }

}
