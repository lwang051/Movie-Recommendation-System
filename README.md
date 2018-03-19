# Movie-Recommendation-System

This project was done without using Github.
### Input
This system takes a plain text file as its input. See the [**input file**](../master/input.txt) here. Each line contains one record in the format of **"userID,movieID,rating"**. For example, "424,786,1.3" means that the user with ID 424 watched the movie with ID 786, and rated it 1.3 stars out of 5 stars.
### Output
This system emits a plain text file as its output. See the [**output file**](../master/Output/output6/part-r-00000) here. Each line contains a recommendation result for a user in the format of **"userID,  recommended_movieID:score"**. For example, "101	470:0.26" means that we recommend the movie with ID 470 to the user with ID 101 because this movie outperforms all the other movies with the highest score of 0.26.
### Task
The task of the system is then reduced to finding the movie with the highest score for the user.

Run RecommendSystem-0.0.6.jar on hadoop with this command:
    hadoop jar RecommendSystem-0.0.6.jar org/lingbo/hadoop/RecommendSystem /input /output1 /output2 /output3 /output4 /output5 /output6
where /output1 to /output6 are the output directories. Final recommendation result is in /output6.
