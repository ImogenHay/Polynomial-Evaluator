My solution to the IDBS Assessment.

Retrievs polynomials from multiple input formats.
Evaluates them using only ADD and SUBTRACT.

CandidateSolution - Contains main method, retrieves data

ReadData - Defines the fields, methods and abstract methods of different input types

ReadJson - Defines the fields and methods of different json input type

ReadNumeric - Defines the fields and methods of different numeric input type

Term - Defines fields and methods of Term

Equation - Defines fields and methods of Equation and allows you to evaluate its terms

Calculations - Interface allowing global access to variable containing previous calculations to reduce repetitions

To add new input format create new class extending ReadData and update determineInputFormat() in CandidateSolution class.




