# workout-uno
Initial Project Description
Project 2:
	NOT ALLOWED:
		-Abstract Data Types
			*Queues
			*Stacks
			*Lists
		-Prebuilt Data Structures included in Java
			*Linked Lists, etc.
					^(any Data Structures we want to use, we must implement from scratch)
	
	POTENTIAL CLASSES NEEDED(*Attributes of each):
		-Main
			*will execute the majority of the code, will include implementations of Deck, Hand, and Card
		-Deck
			*number of cards in deck
			*number of cards in an uno deck is 108
				^every card is repeated once with exceptions
				^every color has only one Zero card
				^four wild cards and four wild draw 4 cards
		-Card
			*Type(ex. Blue,Red,Wild)
			*Face Value(#, Skip, +4, etc.)
		-Hand
			*Card
			*MaxCards = 7
			
	COMMONALITIES ACROSS ALL CLASSES:
		-Getters
		-Setters
		
	NECESSITIES:
		-Every class must be in its own file
			*due to the nature of the classes, the Card class will need to be created first
			 as it will be used in both the Deck and Hand classes
		-Output will be a HTML file
			*Once the program finishes executing
			*Every time a hand is drawn
				^the interpretation of the cards in the hand
				^total number of repetitions per exercise to be done in the round post-rules
				^number of cards left in the pile
				
			
	RESPONSIBILITIES OF EACH MEMBER:
		-Any class they are assigned
			*this includes creating the UML diagram for any classes created
			*also includes writing test cases and relevant comments for any classes written
			*acknowledge collaboration on any classes inside header comments
		-Merging changes onto master branch on github
		
