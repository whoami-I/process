package com.example.processcommunicate.myrxjava;

public abstract class Observable<T> implements ObservableSource<T> {


    public abstract void subscribe(Observer<T> observer);

    public  static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        return new ObservableCreate<T>(source);
    }
    public <V> Observable<V> map(Function<T, V> f) {
        return new ObsevabelMap<T,V>(this, f);
    }

    public Observable<T> subscribeOn(Schedulers schedulers){
        return new ObservableSubscribeOn(this,schedulers);
    }


}
