This folder will contain all documentations  
# Required project plan documentations: #  
1. SDLC rationale  
2. Listing of all phases of SDLC  
3. WBS  
4. Gantt Chart
5. Role Assignments
6. Hour estimation/actuals
7. Project Meeting/meeting documentation
8. System documentations:   
    a)Class explanation  
    b)Class hierarchy diagram  
    c)System limitations  
9. Level 0 Data Flow Diagram (Explanation Within ReadMe)
10. Level 1 Data Flow Diagram  (Explanation Within ReadMe)
# Overall Class Structure: #
![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/UML%20(1).png?raw=true)

# Level 0 Data Flow Diagram #

![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/Level%200%20Data%20Flow%20Diagram.png?raw=true)

This dataflow diagram shows an generalized overview of how information is passed through the chatbot. The diagram generalizes classes and their functions into processes which are labeled with verbs, while data and their sources are labelled with nouns. The input is taken in from the user and is passed on to the processes of checking for spelling mistakes, checking for names and finally checking for sentimental responses, which all three draw data from different sources to check against the input. This is then analyzed within the process of generating a response, which also uses data from our list of potential inputs and responses as well as our responses for inputs outside of the topics assigned to the chatbot. The response is then passed on to the response process where it is shown to the user.