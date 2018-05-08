# Andrew Smith
# RPC
from random import randint

# instructions
print("Jarvis has challenged you to rock, paper, scissors.")
print("What will you choose?")
inpoot = int(input("Rock(1), Paper(2) or Scissors(3)? "))

# intitializing wins and losses
wins = 0
losses = 0

# game loop
while(wins!=3 and losses != 3):

# displaying player choice
    if inpoot==1:
        print("You chose Rock.")
    if inpoot==2:
        print("You chose Paper.")
    if inpoot==3:
        print("You chose Scissors.")

# randoming Jarvis's choice
    rand = randint(1,3)

# displaying Jarvis's choice
    if rand==1:
        print("Jarvis chose Rock.")
    if rand==2:
        print("Jarvis chose Paper.")
    if rand==3:
        print("Jarvis chose Scissors.")

# player  scenario
    if (inpoot==1 and rand==3) or (inpoot==2 and rand==1) or (inpoot==3 and rand==2):
        wins = wins+1
        print("Round won")
        
# Jarvis round win scenario
    if (rand==1 and inpoot==3) or (rand==2 and inpoot==1) or (rand==3 and inpoot==2):
        losses = losses+1
        print("Round lost")

# Tie scenario
    if (inpoot == rand):
        print("Tie, no points given")

# displaying scores
    print("Player:",wins)
    print("Jarvis:",losses)

# player win scenario
    if (wins == 3):
        print("YOU WIN!")
        
# Jarvis win scenario
    elif (losses == 3):
        print("JARVIS WINS, as expected.")
        
# continue if no overall winner
    else:
        inpoot = int(input("Rock(1), Paper(2) or Scissors(3)? "))
