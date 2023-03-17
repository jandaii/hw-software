## Starting the Game

## Set Up Backend Server ##
run the backend server directly under the project file.
```
mvn exec:exec
```
in the back-end folder. This will start the Java server at http://localhost:8080.

## Set Up Frontend Server ##
enter the front-end folder, run

```
cd frontend/
npm install
npm start
```


## Homework 3: Santorini (Part 1)

In this assignment, you will design and implement the core logic of a board game called Santorini (without god cards) in and only in Java. The focus of this assignment is on considering design alternatives for code. In Homework 5, we will revisit the game and extend it with god cards, with a GUI, and with an AI opponent. 

This assignment is intended as a gentle introduction to modeling on a relatively simple problem, but it requires going through all steps of the design process. There are two milestones for this assignment:
* Milestone 3a: You will design your implementation of Santorini and submit design documents by Monday, February 13.
* Milestone 3b: You will implement and test your design of Santorini by Monday, February 20.

This assignment has the following learning goals:

* Demonstrate a comprehensive design and development process including object-oriented analysis, object-oriented design, and implementation.

* Demonstrate the use of design goals to influence your design choices, assigning responsibilities carefully, using design patterns where appropriate, discussing trade-offs among alternative designs, and choosing an appropriate solution. The core logic of your solution must be testable and completely independent from your solution’s eventual graphical user interface (GUI).

* Communicate design ideas clearly, including design documents that demonstrate fluency with the basic notation of UML class diagrams and interaction diagrams, the correct use of design vocabulary, and an appropriate level of formality in the specification of system behavior.

To start the assignment, use the GitHub classroom link from the Canvas assignment to create your personal repository.  It will be empty except for a copy of this document and the appendix; you will check in both your design documents and your code to this repository. Consult the appendix for guidance on how to make a new maven project. 



## 3a: Object-Oriented Analysis & Design

For this milestone, you will analyze and design the game Santorini without god cards (see appendix for rules). You should focus on the game-related functionality of the program, not its user interface. Think of playing the game by calling a sequence of methods, which you could execute in a test case; it is also helpful to think about and possibly sketch out the GUI and how it interacts with the game at this early stage. Note that the game (without god cards) is fairly simple, so you likely won't need more than a few classes/objects/methods.

Before the deadline (signups on Piazza), you will meet with a member of the course staff with your completed design (with documents pushed to repository) to receive feedback on it. The meeting is required and accounts for a portion of points for the 3a milestone. 

For the 3a deadline, we will only be grading for completion. After receiving feedback, you should revise your design documents as necessary as we will be grading them for quality and accuracy with the 3b deadline. 

**Deliverable 1: Domain model.** Create a domain model describing the important concepts of the game. Your domain model should be represented by a UML class diagram; you may optionally include a glossary. For more information on domain models, see Chapter 9 of Larman’s Applying UML and Patterns. Turn this in as <code>domain-model.pdf</code> in the root directory of your git repository.

**Deliverable 2: System sequence diagram.** Create a system sequence diagram identifying all interactions between a user and the system when the user plays the game. The system sequence diagram should help you determine what interactions the high-level system makes available to its users. For more information on system sequence diagrams, see Chapter 10 of Larman’s Applying UML and Patterns. Turn this in as <code>system-sequence-diagram.pdf</code>.

**Deliverable 3: Behavioral contract.** Provide behavioral contracts for the following interaction initiated by the user: _The user attempts to move a worker_. The contract should explicitly describe the preconditions and postconditions for the interaction, and your behavioral contract should be consistent with your domain model and interaction diagrams. Constructing behavioral contracts should help you envision important changes of internal state of the game when a player interacts with the game. You may provide explicit examples to clarify your contract. For more information on contracts, see Chapter 11 of Larman’s Applying UML and Patterns. Turn this in as <code>contract.pdf</code> or <code>contract.md</code>.

**Deliverable 4: Justification.** Write a short report that answers the following questions to justify your design choices. To receive full credit, each of your answers to these questions must both:
Refer to design goals, principles, and patterns where appropriate. 
Discuss the alternatives you had considered and the trade-offs they entailed that led you to choose this particular design (essentially, your design process).
Turn this in as <code>justification.pdf</code> or <code>justification.md.</code>
For questions 2 and 3, embed your diagrams in the <code>justification.pdf/.md</code> file. 

Questions:
1. How can a player interact with the game? What are the possible actions?

2. What state does the game need to store? Where is it stored? Include the necessary parts of an **object model** to support your answer. 

3. How does the game determine what is a valid build (either a normal block or a dome) and how does the game perform the build? Include the necessary parts of an **object-level interaction diagram** (using planned method names and calls) to support your answer.

Diagrams should not be auto-generated from code (see Appendix 2 for UML notation and tool references). Diagrams should be consistent with one another and other diagrams submitted with the homework.

Deliverables for this milestone:
* domain-model.pdf
    * domain model of Santorini
* system-sequence-diagram.pdf
    * system sequence diagram
* contract.pdf (or .md)
    * behavioral contracts for “The user attempts to move a worker”
* justification.pdf (or .md)
    * Question 1 written answer
    * Question 2 written answer
        * Necessary parts of object model
    * Question 3 written answer
        * Necessary parts of object-level interaction diagram

To submit these documents, push them to the root directory of your Santorini repository on Github. Submit a link of the final commit to Canvas.

## Task 2: Implementation & Test

Implement the game **in and only in Java** based on your design and test it. As usual, document your code using Javadoc for all public functions.

It is encouraged that your tests should include unit tests as well as integration tests that set up the game and play sequences of turns. To achieve that, it is a good strategy to write tests while you implement each function, and go back to add a little more tests in case you missed any important test cases and functionality after you complete your implementation.

There is no specific numeric goal for testing (neither for your codes or for our grading), but we expect to see tests of individual key functions (e.g. move and build functions) and tests of sequences of multiple actions of game play. But we are NOT looking for coverage of every possible corner case, and we will NOT inject bugs to evaluate the bug-finding ability of your test suites like Homework 2. Remember that this homework is not all about tests, and you should not spend too much time writing **complete** test suites. However, as a useful and necessary step in software construction, tests are helpful in that they can help you build confidence that your implementation is correct at a high level. 

We would like you to run your code by calling the methods directly. We do not expect a user interface, either in command line or graphical, in this assignment. You may find it useful to create a simple command line UI when you are developing the code, but we don’t expect you to implement or test a command line UI as part of your program/testing.

You should update your design documents from milestone 3a based on insights gained from the implementation. We expect that the submitted models and code align.

Deliverables: Commit all your code to your GitHub repository and ensure that your project is built and tested on Github Actions -- which you will need to set up yourself (see appendix 3). 

## Submitting your work

As in previous homework assignments you push your solution to your Santorini repository on GitHub and submit a link to the final commit to Canvas. A link will look like `https://github.com/CMU-17-214/<reponame>/commit/<commitid>`.

All design documents should be located in the root directory of your repository. 
Please work in the main branch. 

## Evaluation

The homework is worth 200 points. We will grade the homework roughly with the following rubric:

**Graded with 3a milestone:**

**Design milestone (30 pt):**

* [ ] 5: The domain model in file domain-model.pdf is reasonably complete regarding important concepts of the game.
* [ ] 5: The system sequence diagram in file  system-sequence-diagram.pdf is reasonably complete regarding user and system interactions.
* [ ] 5: The behavior contract in file contract.pdf or contract.md is reasonably complete regarding pre- and post-conditions.
* [ ] 15: The report in file justification.pdf or justification.md reasonably attempts to answer the questions and include relevant diagrams.

**Design Discussion with Course Staff (20 pt):**

* [ ] 20: The student is present, prepared with their completed design documents, and attentive during the meeting with the course staff.

**Graded with 3b milestone:**

**Design artifacts (15pt):**

* [ ] 5: The domain model in file <code>domain-model.pdf</code> describes the vocabulary of the problem, uses suitable notation, and is at the right level of abstraction
* [ ] 5: The system sequence diagram in file <code>system-sequence-diagram.pdf</code> is reasonably complete, uses suitable notation, and is at the right level of abstraction.
* [ ] 5: The behavior contract in file <code>contract.pdf</code> or <code>contract.md</code> is reasonably complete regarding pre- and post-conditions.

<strong>Design Quality and Justification (65 pt):</strong>

* [ ]  25: The design across all models makes reasonable decisions about responsibility assignment. The use or lack of design patterns is appropriate. In particular, we will look for: Where is state stored (players, current player, worker locations, towers, winner)? Who checks whether a move/build is valid? Who performs state updates for move/build? Who checks whether the game is over and who the winner is? Is cohesion reasonable, avoiding classes/objects with too many/few responsibilities? Is unnecessary coupling avoided? Is unnecessary complexity avoided?
* [ ] 15: The answers in file `justification.pdf` or `justification.md `all use suitable terminology,  demonstrate an engagement with design principles and tradeoffs, and discuss design alternatives in a meaningful way. 
* [ ] 10: The answers all include at least one of if not both a partial object model and partial interaction diagram to illustrate the discussion.  All diagrams should be reasonably complete within the scope of the question, use suitable notation for that type of diagram, and be at the right level of abstraction. Diagrams should be consistent with one another and other diagrams submitted with the homework.
* [ ] 5: The ways for users to interact with the game, and which part is responsible for the interaction, are clearly stated and justified in the text and the associated in-line diagrams/models. 
* [ ] 5: The locations storing different states are clearly stated (players, current player, worker locations, towers, winner) and justified in the text and associated in-line diagram/models.  
* [ ] 5: The classes and the process that check whether a build is valid are clearly stated in and justified in the text and associated in-line diagrams/models. 

**Implementation (70pt):**

* [ ] 30: All core functionality of the game is implemented and follows all rules as specified. Specifically we will look for: initializing the game, rejecting invalid moves and builds, updating state after moving and building, and determining the winner and ending the game.
* [ ] 10: The implementation aligns with models. We will look for: same names, state and methods in the same classes/objects, associations cardinalities reflect implementation, and interactions possible as shown in diagrams.
* [ ] 5: The public methods of the code are well documented.
* [ ] 10: The student applied the ideas of specification, structural ,and integration tests into their test suites. The key functions like validating a move, a build, and tests of sequences of game play are tested at a reasonable level. The tests follow good practices (e.g. redundancy, independence, readability. NOT including the completeness of test suites).
* [ ] 5: The build and tests are automated on Github Actions.
* [ ] 5: Commits are reasonably cohesive; commit messages are reasonable.
* [ ] 5: The implementation practices reasonable style, and the codes can pass a reasonable linter check (e.g. checkstyle.xml in previous homework).


## Appendix 1: Santorini Rules

Santorini has very simple rules, but the game is very extensible. You can find the original rules [online](https://roxley.com/products/santorini). Beyond the actual board game, you can also find an App that implements the game if you want to try to play it.

In a nutshell, the rules are as follows: The game is played on a 5 by 5 grid, where each grid can contain towers consisting of blocks and domes. Two players have two workers each on any field of the grid. Throughout the game, the workers move around and build towers. The first worker to make it on top of a level-3 tower wins. Note that though the official rules require that if a player cannot further move any worker, she/he will lose, you don't need to consider this as a winning condition in this homework. You also don’t need to handle more than two players.

As set up, both players pick starting positions for both their workers on the grid. (For simplicity, in Homework 3 and 5, **you can assume a player (e.g. Player A) always starts first**). Players take turns. In each turn, they select one of their workers, move this worker to an adjacent unoccupied field, and afterward add a block or dome to an unoccupied adjacent field of their new position. Locations with a worker or a dome are considered occupied. Workers can only climb a maximum of one level when moving. Domes can only be built on level-3 towers. You can assume there are infinite pieces to play.

That's it. You probably want to play a few rounds to get a feel for the game mechanics. There are god powers that modify the game behavior, but those will not be relevant until Homework 5.


## Appendix 2: Notation & Tools

To ease communication and avoid ambiguity, we expect all models to use UML notation for class and sequence diagrams. Chapters 9, 10, 15, and 16 of Larman’s Applying UML and Patterns provide many details and guidance on UML notation. We do not require much formality, but we expect that associations are used rather than attributes where appropriate and that each association includes a name and cardinalities. Attributes and methods should be specified correctly, but we do not require precise descriptions of visibility or types. 

It is important that your models demonstrate an understanding of appropriate levels of abstraction. For example, your domain model should not refer to implementation artifacts, and your object model should not include low-level details such as getter and setter methods, unless they aid the general understanding of your design. 

UML contains notation for many advanced concepts, such as loops and conditions in interaction diagrams. You may use UML notation for these advanced concepts, but we do not require you to do so. If you find you need advanced concepts, you may describe such concepts with your own notation or textual comments, as long as you clearly communicate your intent. 

To maximize clarity, we recommend that you draw UML diagrams with software tools. We do not require specific tools, and you may share tool-related tips on Piazza. There are several easy to use online tools like [Draw.io](https://draw.i/) and [Yumly](https://yuml.me/), and also many desktop tools and IDE plugins. We strongly recommend that you do not mechanically extract models from a software implementation; such mechanically generated models are almost always at an inappropriate level of abstraction. We will accept handwritten models or photographs of models (such as whiteboard sketches) if the models are clearly legible.


## Appendix 3: Setting up your Java Project

Please refer to Appendix3.pdf.
