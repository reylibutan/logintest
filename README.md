# logintest
Programming Test using Spring stack

# Database Schema
The database schema is [`20160927_DB_data.sql`](https://github.com/reylibutan/logintest/blob/master/20160927_DB_data.sql).

# Generating Dummy Data
To generate dummy data, run [`LogintestDataLoader.java`](https://github.com/reylibutan/logintest/blob/master/src/main/java/com/libutan/rey/logintest/LogintestDataLoader.java). Additionally, you can change some configuration to generate the data as desired.
Such as the following:

```
private static long MIN_TIME = 1472659200000L; // September 01, 2016 00:00:00 (GMT + 8)
private static long MAX_TIME = 1475251199000L; // September 31, 2016 23:59:59 (GMT + 8)
private static int NO_OF_RECORDS = 100_000;
private static String[] NAMES = new String[] {"Feng Wei", "Wang Jinrei", "Raven", "Eleonore Kliesen", "Kuma", "Panda", "Lee Chaolan"};
private static String[] ATTRIBUTES = new String[] {null, "red", "orange", "yellow", "green", "blue", "indigo", "violet"};
```
