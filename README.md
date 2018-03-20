# Movie-Recommendation-System

This project was done without using Github.
### Command
Upload **_RecommendSystem-0.0.6.jar_** and **_input.txt_** on Hadoop 2.7.3. Run it with this command:

    hadoop jar RecommendSystem-0.0.6.jar org/lingbo/hadoop/RecommendSystem /input /output1 /output2 /output3 /output4 /output5 /output6

Final recommendation result is in /output6.
### Input
1. This system takes a plain text file as its input. See the [**input file**](../master/input.txt) here.
2. Each line contains one record in the format of **"userID,movieID,rating"**.
3. For example, "424,786,1.3" means that the user with ID 424 watched the movie with ID 786, and rated it 1.3 stars out of 5 stars.
### Output
1. This system emits a plain text file as its output. See the [**output file**](../master/Output/output6/part-r-00000) here.
2. Each line contains a recommendation for a user in the format of **"userID    recommended_movieID:score"**.
3. For example, "101	470:0.26" means that we recommend the movie with ID 470 to the user with ID 101 because this movie outperforms all the other movies with the highest score of 0.26.
### Task
The task is then reduced to finding the movie with the highest score for each user.
### Scoring
1. Item-item collaborative filtering is used. Matrix multiplication is performed after normalization.
2. [**This schema**](../master/docs/Scoring%20Schema.pdf) shows how to get each movie's score for a user. The idea is that "You rated movie1 high, and there are many people watched both movie1 and movie2. If you haven't watched movie2, you should try it".
