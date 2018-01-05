# Music-Meta-Data-Processing-Using-Hadoop-Map-Reduce



README

This project has 4 packeages: org.Table0, org.Table1, org.Table2, org.Table3.

org.Table0
=============
org.Table0 merges two tables using the common track_id.

org.Table1
=============
org.Table1 merges the output of org.Table0 with the third table using the common key artist_id.

org.Table2 and org.Table3
=========================
org.Table2 and org.Table3 are a word counting problem for a specific field.
org.Table2 counting the number of tracks for each location.
org.Table3 counting the number of tracks for each year.


How to run
==========
Before running the code you need to use these configurations:

For org.Table0 use "/input0 /output0"

For org.Table1 use "/input1 /output1"

For org.Table2 use "/input2 /output2"

For org.Table2 use "/input3 /output3"

* Make sure to delete the "output#" directory before running again.
* All the input directories are ready and the needed files are already inside these input directory.

Deign and Implementation
=============================
This project using Hadoop mapreduce libraries and we have at least 1 mapper function and 1 reducer function.
For task0 and task1, two mapper were used and one reducer. 
The class "MultipleInputs" was used to specify the input for each file and to identify a mapper for a specific file.
For task2 and task3 a template for a regular wordcount class is used to count a specific field and produce the output.
For task2 and task3, because the keys are "location" and "year" respectively, the output will be ordered accordingly.
For all the tasks, the mapper is reading the file line by line and then specifying the required filed using an array
and the values (fields) where splitted using the "split" function according to the separator "<SEP>".
For task0 and task1, one reducer is used and a specific file tag was used to identify from which mapper 
the input is? This is because each mapper produce different output.
For task2 and task3, one mapper is used with the same technique of separation. In the reducer a simple counting was used.






This project has many tasks about data processing in Hadoop and all are listed below:

Task 0: Merge first two metadata tables into a new Table0. 

Output must be in following format:

track id<SEP>year<SEP>artist id<SEP>artist name

Example 1: For each ‘track id’ display ‘track year’ and artist information as below.

TRWWWWT128F92F7BE5<SEP>2004<SEP>ARME0V21187B99A5B8<SEP>Gerty Farish


Task1: Merge output of Task0 (Table0) with the third table and produce a new Table1. 
Build a new table (Table1) that has information of artist location, artist id, track id and year.



Task2: 
1) Find number of tracks for each location and produce a new Table2.

Output:  location<SEP> (#of tracks)

Example: Columbus, OH<SEP>10

2) Find number of tracks for each year. Output must be ordered by year. Produce a new Table3.

Output:  year<SEP> (#of tracks)

Example: 2004<SEP>10

