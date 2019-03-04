### Task instructions

Starting from the "Gilded Rose Requirements Specification" below, accompanied by a partial implementation, 
the candidate must add incrementally some features while ensuring the quality of the development process and produced code.

The partial implementation is very simplistic and of bad quality. It is accompanied by a little test class 
called "Golden Master" that can run the current code. It has no build system integration or version control.

The candidate must at all time strives for ensuring:

- An object oriented design of the solution.
- A validation of the correctness of the solution, for example using tests.
- An incremental building of the solution via the use of small self-consistent commits.
- The use of English as the communication language for code, commits and documentation.
- And finally, the balance between perfection and the time available for the exercise by:
  - Keeping tab on desired future improvements, for examples using TODOs,
  - Making and documenting design choice in case of ambiguities in the problem,
  - Being practical.


The following tasks are expected of the candidate, preferably in this order:

- Setup the project with build and version control, preferably with Maven and git.
- Publish it on a version control platform for the reviewers to access (github, gitlab, etc).
- On top of the existing implementation, add the 3 following independent items:
- Legendary items, Backstage passes, Conjured items (cf requirements),
- The Golden Master is there for reference of an acceptable item creation API but can be changed if desired.
- Give to the user the ability to view the content of a virtual inn's inventory by:
  - Exposing a REST-like API to retrieve the information,
  - Providing a minimal Web UI to preview the items and their characteristics,
  - Aesthetics have no importance at all in this task.
  - Give to the user the ability to express, via this UI, that a day has passed so that the items degrades as expected.
  - Add a functionality that let the user know, in the inventory, which item is of the best quality for every item type
- As a simplification, we consider that two items with the same name has the same type. 

The text below describes the Gilded Rose domain and problem.


### Gilded Rose Requirements Specification


Hi and welcome to team Gilded Rose. As you know, we are a small inn with a prime location in a
prominent city ran by a friendly innkeeper named Allison. We also buy and sell only the finest goods.
Unfortunately, our goods are constantly degrading in quality as they approach their sell by date. We
have a system in place that updates our inventory for us. It was developed by a no-nonsense type named
Leeroy, who has moved on to new adventures. Your task is to add the new feature to our system so that
we can begin selling a new category of items.

First an introduction to our system:

  - All items have a SellIn value which denotes the number of days we have to sell the item
  - All items have a Quality value which denotes how valuable the item is
  - At the end of each day our system lowers both values for every item

Pretty simple, right? Well this is where it gets interesting:

  - Once the sell by date has passed, Quality degrades twice as fast
  - The Quality of an item is never negative
  - "Aged Brie" actually increases in Quality the older it gets
  - The Quality of an item is never more than 50
  - "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
  - "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
	Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
	Quality drops to 0 after the concert.

We have recently signed a supplier of conjured items. This requires an update to our system:

  - "Conjured" items degrade in Quality twice as fast as normal items

Just for clarification, an item can never have its Quality increase above 50, however "Sulfuras" is a
 legendary item and as such its Quality is 80 and it never alters.