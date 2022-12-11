# Eindopdracht-1
De uitwerking van de eerste eindopdracht.

## Description

In dit project maken we gebruik van 2 backend services, namelijk taskservice en accountservive. Verder hebben we ook een frontend.

## Getting Started

Start de aws omgeving op in aws labs voor de reparatie maken we nu gebruik van de omgeving van Tygo Steenbergen, aangezien de andere omgeving over budget was.
Check dat de images draaien in de ec2 instances, zo niet zet deze aan met "docker start [OPTIONS] CONTAINER [CONTAINER...]".
Zet de databases aan voor de ec2 instances dit zijn task-database en account-database.
De frontend is beschikbaar op http://d1val08eg2t11.cloudfront.net/
Controleer even voor de zekerheid dat je op http zit ipv https.

## Documentatie
### Use-case Diagram: ###
![UseCaseDiagram](https://user-images.githubusercontent.com/60653502/206901823-7114d450-2962-44f4-984b-10c5dd6c8d6c.png)
### Component Diagram: ###
![ComponentDiagram](https://user-images.githubusercontent.com/60653502/206901874-7a6ae740-9770-42bd-aaf3-dd9f12f1f0c2.png)
### Domein model: ###
![DomainModel](https://user-images.githubusercontent.com/60653502/206901905-7036a501-1d4c-400f-b5d8-c374ecd823ce.png)
### Cloud Diagram: ###
![CloudDiagram](https://user-images.githubusercontent.com/60653502/195855388-2ac094fd-a14c-43e3-9ff7-121a71acb907.png)
### Activity Diagram voor "login" use case: ###
![ActivityDiagramLogin](https://user-images.githubusercontent.com/60653502/195855425-eb2cfdbb-caa5-4385-88ef-67607af60816.jpeg)
### Activity Diagram voor "move tasks between columns" use case: ###
![ActivityDiagramMoveTasksBetweenColumns](https://user-images.githubusercontent.com/60653502/195855426-41d89f4c-fd46-4a3a-9ea5-a723e7eeba8b.png)
### Activity Diagram voor "register" use case: ###
![ActivityDiagramRegister](https://user-images.githubusercontent.com/60653502/195855428-f3bf1203-2662-4c17-82d3-694493ef1154.jpeg)
### Activity Diagram voor "add task" use case: ###
![ActivityDiagramAddTask](https://user-images.githubusercontent.com/60653502/195855442-05c64489-9b9b-4358-be35-e0e34452ac64.png)
### Sequence Diagram voor "add user to board" use case: ###
![ActivityDiagramAddUserToBoard](https://user-images.githubusercontent.com/60653502/195855443-46235d17-dd96-47e4-9a4d-4457ef934930.png)
### Sequence Diagram voor "add task" use case: ###
![SystemSequenceDiagramAddTask](https://user-images.githubusercontent.com/60653502/195855431-1e7dc494-a26d-41bc-ad20-2fea1ec50b45.png)
### Sequence Diagram voor "add user to board" use case: ###
![SystemSequenceDiagramAddUserToBoard](https://user-images.githubusercontent.com/60653502/195855435-78a289cd-a729-4483-b96e-99dc0e09d71d.png)
### Sequence Diagram voor "move tasks between columns" use case: ###
![SystemSequenceDiagramMoveTasksBetweenColumns](https://user-images.githubusercontent.com/60653502/195855441-11f29d60-e270-417d-ba85-6d6baf11c34b.png)
