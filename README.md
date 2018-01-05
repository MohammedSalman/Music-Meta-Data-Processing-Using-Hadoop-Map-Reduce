# Music-Meta-Data-Processing-Using-Hadoop-Map-Reduce




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

