To test the following sorting algorithms, run:

javac -Xlint Sorting.java

to compile. Followed by:
java Sorting


If you want to test the individual files using your own testing suite, below is example code to test each version of the file.  In this code, we pass in an array to be sorted called “forSorting”:

VERSION 1
StackSorterV1 L = new StackSorterV1();
L.sort(forSorting);


VERSION 2
StackSorterV2 L = new StackSorterV2();
L.sort(forSorting);

VERSION 3
StackSorterV3 L = new StackSorterV3();
L.sort(forSorting);

This code was compiled and run using:
java version "1.8.0_66"
Java(TM) SE Runtime Environment (build 1.8.0_66-b17)
Java HotSpot(TM) 64-Bit Server VM (build 25.66-b17, mixed mode)