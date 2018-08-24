## zipcode-reduction-demo
### Installation and command line execution
Assuming you have git and maven setup, you can sync, build and test this project using the following steps:

#### In the directory of your choice type: 
```git clone https://github.com/witcradg/zipcode-reduction-demo.git```

#### Change directories to the project root and compile:
```cd zipcode-reduction-demo/zipcodereduction/```

```mvn compile```

#### to run the unit tests type:
```mvn test```

#### to build an executable type:
```mvn package```

#### to run the jar 
```cd target/```

#### The jar file will be named something like zipcodereduction-1.0-SNAPSHOT.jar
#### Run it by typing:

```java -jar zipcodereduction-1.0-SNAPSHOT.jar```

#### you will be prompted at the command line with 
```Enter a set of ZIP code ranges>```

#### You can enter your own or copy one of the tests and paste it in. ```Hit Enter```.
#### The output will be written back to the terminal.

#### Example input:
#### [11111,22222] [15555,25555] [23333,33333] [24444,35555]

#### More sample inputs can be found below in the original problem statment section.

#### If you type in an invalid collection you will get an error message, a usage statement and the exception the entry caused.

```An invalid collection was entered```

```Enter a collection of one or more pairs of ZIP code values```

```surrounded by brackets in this format: [94133,94133]```

```All values should be entered on the same line.```


#### The following is the original problem statement:
BACKGROUND
Sometimes items cannot be shipped to certain zip codes, and the rules for these restrictions are stored as a series of ranges of 5 digit codes. For example if the ranges are:

[94133,94133] [94200,94299] [94600,94699]

Then the item can be shipped to zip code 94199, 94300, and 65532, but cannot be shipped to 94133, 94650, 94230, 94600, or 94299.

Any item might be restricted based on multiple sets of these ranges obtained from multiple sources.

PROBLEM
Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower bounds), provide an algorithm that produces the minimum number of ranges required to represent the same restrictions as the input.

NOTES
- The ranges above are just examples, your implementation should work for any set of arbitrary ranges
- Ranges may be provided in arbitrary order
- Ranges may or may not overlap
- Your solution will be evaluated on the correctness and the approach taken, and adherence to coding standards and best practices

EXAMPLES:
If the input = [94133,94133] [94200,94299] [94600,94699]
Then the output should be = [94133,94133] [94200,94299] [94600,94699]

If the input = [94133,94133] [94200,94299] [94226,94399] 
Then the output should be = [94133,94133] [94200,94399]

Evaluation Guidelines:
Your work will be evaluated against the following criteria:
- Successful implementation
- Efficiency of the implementation
- Design choices and overall code organization
- Code quality and best practices
