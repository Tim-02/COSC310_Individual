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
# Level 0 Data Flow Diagram #

![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/Level%200%20Data%20Flow%20Diagram.png?raw=true)

​	This dataflow diagram shows a generalized overview of how information is passed through the chatbot. The diagram generalizes classes and their functions into processes which are labeled with verbs, while data and their sources are labelled with nouns. 

​	The input is taken in from the user through the GUI and is then broken up into Parts of Speech. The input is then analyzed to produce a response through several processes. First, the input is checked for sentiment and a sentimental response is returned if so. Next if no names are present, the input is "stemmed" or each word of the input is turned into a root for easier analysis. If there is a name present, the input is not stemmed and the name is identified and used in a response phrase including that name. Finally, a list of keywords and phrases are used to produce canned responses to topics if none of the other methods have produced a response. The response is then printed on the GUI alongside the input for the user to see.

![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/fd0bcfeda9833c60a80bb416c56657d18d4f362c/Documentations/Level%201%20Data%20Flow%20Diagram.png?raw=true)

​	This dataflow diagram shows a detailed view of how data is passed through the program at a class level. This diagram also uses verb phrases for processes and nouns for data and data sources, but it also identifies the class in which each process is performed in bolded text. Processes are grouped together based on each class to aid clarity, with the exception of the two uses of the PersonFinder class as one of them is reliant on the returned value of the other.

​	The User gives input through the GUI which is facilitated by the Contents Class and this input is then passed on to the OutputTerminal Class. The input is passed on to the POSTagger Class as well as the Chatbot Class. The POSTagger class retrieves the sentence tokens and parts of speech from the .bin files within the models folder, breaks up and identifies the parts of speech of the input and then prints these parts of speech to the console. The Chatbot class takes the input from the OutputTerminal Class and uses four different classes in it's analysis and production of a response. Firstly the input is passed to the SentimentAnalyzer Class if the phrase begins with "you" and returns a sentimental response based on the degree of sentiment. Next the input is passed to the PersonFinder Class and a boolean determining whether a name is present in the input is returned. Next if the PersonFinder Class was unable to find a name, the input is passed to the Stemmer Class where each word of the input is broken into their roots to aid analysis. If the PersonFinder class did find a name, then the input is passed to the PersonFinder class again and the name is used in a response format that is returned. Finally, the Rules Class retrieves the list of keywords and phrases from the word.txt file and passes this list to the Chatbot Class. If none of the previous classes have produced a response and a response can't be chosen from the list based on the topic keywords, then the Chatbot class will randomly pick one of five canned responses. The response is then returned to the OutputTerminal Class where both the input and response are printed on the GUI for the User to see.

# Overall Class Structure (Assignment 2): #

![alt text](https://github.com/COSC310-Project-Group-8/AssignmentTwo/blob/main/Documentations/UML%20(1).png?raw=true)

