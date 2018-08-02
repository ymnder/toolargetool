# toolargetool

A tool for debugging `TransactionTooLargeException` on Android.

## Usage

1. Include `toolargetool` as a dependency, you can remove it again once you've debugged your crash:

       implementation project(':toolargetool')
       testImplementation project(':toolargetool_op')

2. Add code to start logging during app start, for example in your `Application.onCreate` method:

       new TooLargeTool().startLogging(this); // Java
       TooLargeTool().startLogging(this); //Kotlin

3. Set LoggerParam if use custom logger:

       // Java
       new LoggerParam.Builder()
               .activityCallback(/* callback bundle */)
               .fragmentCallback(/* callback bundle */)
               .build();

       // prepare Utils to easily handle log
       UtilsKt.format()
       UtilsKt.bundleBreakdown()

4. Monitor logcat output to see which components are writing substantial data to the transaction
   buffer and when:

       $ adb logcat -s TooLargeTool

   Example logcat output (TODO: improve this example):

       D/TooLargeTool: MainActivity.onSaveInstanceState wrote: Bundle@200090398 contains 1 keys and measures 0.6 KB when serialized as a Parcel
                                                                               * android:viewHierarchyState = 0.6 KB