# Informational Security - Laboratory Work # 3

&nbsp; &nbsp; In this laboratory work I used **Dictionary Attack** to decrypt the messages. The premise of this method is to have a dictionary of most common used words of the language, as you try every possible shift, you check if the words from the dictionary are found in the message you are trying to decrypt.  

## Project Structure  

`main`----`java`----`decoder`---`AbstractFactory.java`  
&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;| &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |----`AbstractFactoryCache.java`   
&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;| &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |----`DataAnalysisUtilsFactory.java`  
&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;| &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |----`DataProcessingUtilsFactory.java`  
&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;| &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |----`Decoder.java`  
&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;| &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |----`FactoryProducer.java`  
&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;| &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |----`IDataAnalysisUtil.java`  
&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;| &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;| &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |----`IDataProcessingUtil.java`  
&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;| &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; |----`utils`-----`CombinationsUtil.java`  
&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;| &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |----`DictionaryUtil.java`    
&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;| &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |----`FileReaderUtil.java`  
&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;| &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |----`ShiftUtil.java`  
&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;| &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |----`StatisticsCollectorUtilI.java`  
&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;| &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |----`StatsAnalyzerUtil.java`  
&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;|---`resources`----`languages`  
&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |----`messages` 

## Project Analysis

&nbsp; &nbsp; Classes `AbstractFactory` and `FactoryProducer` are used to provide the implementation of *Abstract Factory Pattern*. Using these classes, class `Decoder` gets one of the types of factories (`DataAnalysisUtilsFactory`, `DataProcessingUtilsFactory`).  

&nbsp; &nbsp; Class `AbstractFactoryCache` is used to provide implementation of the *Prototype Pattern*. On loading cache method call, are created 2 factories, which are stored in a `Hashtable` in case one of the factories will be used in more places in the program.  

&nbsp; &nbsp; Classes `DataAnalysisUtilsFactory` and `DataProcessingUtilsFactory` are used to provide the implementation of the *Factory Method Pattern*. Utils are divided into 2 types `DataAnalysisUtils` and `DataProcessingUtils`. `DataAnalysisUtilsFactory` class is used to get utils like combinations util, file reader and shift util; on the other hand `DataProcessingUtilsFactory` is used to get the following utils: dictionary util and statistics collector/analyzer utils.  

&nbsp; &nbsp; Class `Decoder` represent the Decoder. The user will use this class to decode the message.  

&nbsp; &nbsp; Class `CombinationsUtil` is used to calculate all the possible combinations of a message.  

&nbsp; &nbsp; Class `ShiftUtil` is used to shift a message `n` times, where `n` is the # of letters in the alphabet (I used 26 in the latin alphabet and 32 in the cyrillic one).  

&nbsp; &nbsp; Class `DictionaryUtil` is used to read the dictionaries, which are under `resources-->languages` and call the statistics collector for each of the languages.  

&nbsp; &nbsp; Class `StatisticsCollectorUtilI` is used to coolect the statistics about frequency of the words, from dictionaries, in all the possible combinations of the encrypted message. The technique I used is the following: if a word from dictionary is found in one of the combinations of the message, i give, to the corresponding key and langues, some points equal to the length of the word found (e.g. util found String `hello` in the combination `x` of the language `y`, then this pair gets 5 pts).     

&nbsp; &nbsp; Class `StatisticsCollectorUtilI` is used to analyze the calculated statistics. It finds and returns the pair (key, decoded message) which has the most pts.

## Program Results

For the message given in the task:

`Key: 16;`  
`Message:`  
`MAXIMUS: MY NAME IS MAXIMUS DECIMUS MERIDIUS, COMMANDER OF THE ARMIES OF THE NORTH, GENERAL OF THE FELIX LEGIONS, LOYAL SERVANT TO THE TRUE EMPEROR, MARCUS AURELIUS.
FATHER TO A MURDERED SON, HUSBAND TO A MURDERED WIFE. AND I WILL HAVE MY VENGEANCE, IN THIS LIFE OR THE NEXT.`  

`Key: 7;`  
`Message:`  
`EVERY PLAN IS TO BE CONSIDERED, EVERY EXPEDIENT TRIED AND EVERY METHOD TAKEN BEFORE MATTERS ARE BROUGHT TO THIS LAST EXTREMITY.
GOOD OFFICERS DECLINE GENERAL ENGAGEMENTS WHERE THE ODDS ARE TOO GREAT, AND PREFER THE EMPLOYMENT OF STRATAGEM AND FINESSE TO DESTROY THE ENEMY AS MUCH AS POSSIBLE WITHOUT EXPOSING THEIR OWN FORCES.`  

`Key: 21;`  
`Message:`  
`MY LOVE FOR THE ROMAN EMPIRE IS UNDENIABLY GREATER THAN FOR MYSELF.
THE GREATEST EMPIRE EVER TO HAVE EXISTED. I PLEDGE MY ETERNAL SERVITUDE AND I AM FOREVER BOUND TO SERVE IT, IN LIFE AND IN DEATH.
THEY HAVE MERELY GIVEN US: ROADS, CENTRAL HEATING, CONCRETE, THE CALENDAR, AND FLUSHING TOILETS AND SEWERS.`
