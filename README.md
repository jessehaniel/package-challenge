# package-challenge
This a exercise of the famous algorithm Knapsack problem (https://en.wikipedia.org/wiki/Knapsack_problem).
Like the traditional problem, each item is described by an index, its weight and the cost (including the euro 
symbol for input and exhibition). Moreover, the following constraints were added: <br>
<li> package max_weight <= 100
<li> item max_weight and cost, both <= 100
<li> up to 15 items to choose from
<br>

The solution is a library (i.e. maven dependency) where the main class is _Packer_ and the main method is _pack_.

The code has unit test coverage for all the real features (not for getters and setters).