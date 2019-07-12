# Package challenge: implementation of the Knapsack problem

## Introduction
This is an exercise based on the famous algorithm Knapsack Problem (https://en.wikipedia.org/wiki/Knapsack_problem).
Like the traditional problem, you want to choose `Items` to pack in a knapsack (`Pack`). Each Item has an index, weight, and value. The Pack has a maximum weight capacity, so the goal is to determine which items to put into the package so that the total weight is less than or equal to the package limit and the total value is as large as possible. Moreover, the following constraints were added:
<li> package max_weight <= 100
<li> item max_weight and value, both <= 100
<li> up to 15 items to choose from

## The algorithm implementation
The algorithm was constructed beginning with an input validation by the regular expression `^\d+(\.\d+)?:(\(\d+,\d+(\
.\d+)?,€\d+(\.\d+)?\))+$`. Then each line of the file is converted to a Pack representation, containing the maximum weight followed by a list of items. After that, each Pack object is screened (`PackScreening`) whether by its weight (below the package capacity). To all the eligible Packs the algorithm sorts them by its value descending. Each Item tries to fit in the Pack resulting a `Solution`, having the Item's indexes chosen to be packed. The output contains one line of `Solution` for each line of `Pack` in the input.

## The code
The code project is a multi-module Maven project having the submodules **packer** as the main library and **tester** as a sample consumer. The main class of the packer module is `Packer`, which has the static method `pack`. 

The code is written in Java 11 and has test coverage with JUnit 5 for 100% of the classes, 93% of methods, and 94% of lines. The functional programming is predominant with a declarative style along with the code. That give us methods following the Single Responsibility Principle, which is one of the pillars for clean code. Also, the tests follow the FIRST principle (Fast-Independent-Repeatable-Self-checking-Timely). Finally, Log4j2 is used for better monitoring the code behavior.

## Decisions
<li>It was prioritized the most valuable item over a combination of items in which the total sum is higher.
<li>In case of an invalid line in the input file, the execution is aborted.
<li>Trailing zeros from decimal numbers were removed, so the number 1.30 becomes 1.3. So, it isn't possible to compare the input and output strings.
<li>The € symbol is part of the validation regex.
<li>An empty `Solution` is represented by -
