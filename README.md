# Little Pay Coding Challenge

## Assumptions

For the purposes of this exercise I have assumed that bus journeys will not start on one day and finish the next.
Only taps made on the same day are processed to create trips. 

The CSV file will have taps from multiple days and they may not be listed in chronological order in the file. 

A person (identified by PAN) could have multiple trips on a given day.

Each person could have a combination of completed, incomplete or cancelled trips on the same day. 

In the event a person 'taps off' as they are leaving a bus
without having tapped on, because the system has not had a 'tap on' recorded, them tapping off is assumed to have been recorded as a tap on.
In other words, I have assumed that there will be no taps of type 'off' that does not have a corresponding tap on. 


## How to run

Clone the repository to your local folder. 

Open using your IDE of choice and run clean and install. 

Go to the Main java class and run Main.main().

I have a taps.csv file stored under the resources folder. 

On running the main method this file will be read for taps and processed as trips. 

This csv file is referenced in my CSVProcess.java class.

If the run is successful you will receive an output in the terminal with the following words "Trips written to file successfully!"

Go to the file called trips.csv which is below this README. 

This file is where the processed trip were written to. 

I have a suite of test cases under the test > java folder.

Thank you!


