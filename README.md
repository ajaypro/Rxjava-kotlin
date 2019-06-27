
### Using RxJava 2.0 Library in your application

Add this in your build.gradle
```groovy
compile 'io.reactivex.rxjava2:rxjava:2.2.2'
```
If you are using RxAndroid also, then add the following
```groovy
compile 'io.reactivex.rxjava2:rxandroid:2.1.0'
```

# Operators :
* `Map` -> transform the items emitted by an Observable by applying a function to each item
* `Zip` -> combine the emissions of multiple Observables together via a specified function and emit single items for each combination based on the results of this function
* `Filter` -> emit only those items from an Observable that pass a predicate test
* `FlatMap` -> transform the items emitted by an Observable into Observables, then flatten the emissions from those into a single Observable
* `Take` -> emit only the first n items emitted by an Observable
* `Reduce` -> apply a function to each item emitted by an Observable, sequentially, and emit the final value
* `Skip` -> suppress the first n items emitted by an Observable
* `Buffer` -> periodically gather items emitted by an Observable into bundles and emit these bundles rather than emitting the items one at a time
* `Concat` -> emit the emissions from two or more Observables without interleaving them
* `Replay` -> ensure that all observers see the same sequence of emitted items, even if they subscribe after the Observable has begun emitting items
* `Merge` -> combine multiple Observables into one by merging their emissions
* `SwitchMap` -> ransform the items emitted by an Observable into Observables, and mirror those items emitted by the most-recently transformed Observable

### TODO 

* examples are to be added for remainin subject and realtime practical implementations of rxjava

### Contributing to RxJava 2 Android Samples
Just make pull request. You are in!

 
