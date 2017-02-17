# Design-discussion
===================

The main pros and cons of the four individual designs are listed here as in team discussion.


## Design 1
![dlee317](../Design-Individual/dlee317/design.png)



> **Pros:**

> - The designed UML is very self-explanatory and easy to navigate.
> - The design demonstrates good use of class inheritance, class organization and methods.
> - An application class is designed to initiate the tournament and fetched the tournament result.

> **Cons:**

> - The design of an user class can be unnecessary in implementation.
> - Inheritance from user class makes player or manager less flexible for attribute changes.
> 


## Design 2
![ywang3134](../Design-Individual/ywang3134/design.png)


> **Pros:**

> - Detailed structure and methods are designed and simple to follow.
> - Logical relationships between classes are demonstrated and separate classes to represent database entries are designed.

> **Cons:**

> - Over-complicated classes design can cause clutter in implement. Some less-optimal classes only performed one or two operations.  
> - In sufficient use of inheritance to simplify design.
> - Font in UML is too small.
> 


## Design 3
![jtran46](../Design-Individual/jtran46/design.png)


> **Pros:**

> - The simple design can potentially make implementation and maintenance easy. 
> - Entities are clearly identified.  

> **Cons:**

> - The methods within each class are not clearly defined.
> - Some relationships between entities are not explained.
> - It is not clear from the design how players are added and removed.
> 

## Design 4
![rgong3](../Design-Individual/rgong3/design.png)


> **Pros:**

> - The structure is clean with minimal amount of classes.
> - There aren't any unnecessary attributes or operations.
> - The design has a database interface class.
> - Methods are well defined in classes to perform the action.

> **Cons:**

> - It is risk-prone to allow access the database for every class. 
> - This simple design without inheritance can impede the addition of any future functionality.
> 



# Team Design



Design 2

Commonalities:

The team design and design 2 has the same ways to represent tournament and match class. Some operations and parameters in tournament class are similar.
 
Differences:

Team design has player and manager inherated from user class. The TournamentResult class is better organized and contains necessary methods. An application class in team design is used to initiate the tournament and fetched the tournament result. Therefore, the team design UML demonstrates good use of class inheritance, class organization and methods.

Justification:
Good middle ground between abstraction and simplicity.  Reduce amount of redundancies while supporting the requirements, and the design still leaves room for flexibility in case requirements do change.


Design 4

Commonalities:

The team design and design 4 share similar classes by representing a Manager, Player, Match, and Tournament, as well as a utility database class.
 
Differences:

The utility database class in team design is better organized in terms of how it can be accessed and managed. In addition, the team design demonstrates good use of class inheritance, which will accommodate future design requirements more easily. The team design is also more self-explanatory on how each method is implemented. 

Justification:
The team design covers every aspect of requirements with good details. It also demonstrates clear relationships, and flexible to accommodate additional design requirements.
 
# Summary

Group dicussion generated more ideas to make a better design. Everyone had an unique perspective to the design. The relationship between classes became clearer. The designs should be more abstract with less implementation details. Some redundancy in classes are removed.  

Had some back and forth between simplicity of the design for ease of implementation vs a more object-oriented design for a more robust solution.
