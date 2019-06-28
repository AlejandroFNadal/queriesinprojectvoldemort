# queriesinprojectvoldemort
There are none good places to find information about Project Voldemort. Actually, I recommend you to not use Project Voldemort. But, if you must, here you will find some simple examples to work with. Have fun. P/D: We werent able to make the python and C clients to work, so we had to do this in Java. My team, myself included, hates Java, but we had no choice on the matter either.

To use Project Voldemort, first clone the repository of Voldemort

git clone https://github.com/voldemort/voldemort

Then, if you dont have it installed, download JDK from Oracle page. You will be forced to create an Oracle account. 

Install it.

Restart your computer, especially if you are in Windows.

Go to the voldemort folder.

Run this in Linux or Mac

./gradlew build -x test

or this in Windows

gradlew build -x test

It will take a while. Prepare yourself a nice coffee

There, run the next command

In Linux or Mac
bin/voldemort-server.sh config/single_node_cluster

In Windows:

bin\voldemort-server.bat config̣\single_node_cluster

Then, open a new console. Go to the voldemort folder, and run:

In Linux or Mac
bin/voldemort-shell.sh test tcp://localhost:6666

In Windows:

bin\voldemort-shell.bat test tcp://localhost:6666

If all these worked (it is rather unlikely), you will be presented with a prompter. That is a voldemort shell. Try this

put "hello" "world"

get "hello"

It should print "world". If it does, congratulations, you have a working Voldemort cluster.

For those lucky enough to have reached this point, but also, unlucky enough to need more:

We were not able to make the Python or C clients to work, so, Java it is.

Inside voldemort, go to example/java/voldemort/examples

Then, if you are in a Linux environment, run ./build and then ./run

We were not yet able to make this work in Windows. As soon as we make it, we will explain how to do so.

Well, lets suppose you are using Linux. You will be presented with some successfull data entries and also a few data retrievals. Now, you just gotta play with Java. Have fun

Also, we recommend to our teacher to cross Voldemort out of the list of working NoSQL. It is rather broken. Perhaps it is a matter of our lack of experience, but we would not recommend this database to anyone who is a newbie as we are. 
