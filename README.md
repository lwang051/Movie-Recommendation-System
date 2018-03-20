# Movie-Recommendation-System

This project was done without using Github.
### Command
Upload **RecommendSystem-0.0.6.jar** and **/input** on Hadoop 2.7.3. Run it with this command:

    hadoop jar RecommendSystem-0.0.6.jar org/lingbo/hadoop/RecommendSystem /input /output1 /output2 /output3 /output4 /output5 /output6

Final recommendation result is in /output6.
### Input
1. This system takes a plain text file as its input. See the [**input file**](../master/input/input.txt) here.
2. Each line contains one record in the format of **"userID,movieID,rating"**.
3. E.g. "424,786,1.3", user424 watched the Movie786, and rated it 1.3 stars out of 5 stars.
### Output
1. This system emits a plain text file as its output. See the [**output file**](../master/output/output6/part-r-00000) here.
2. Each line contains a recommendation for a user in the format of **"userID    recommended_movieID:score"**.
3. E.g. "101	470:0.26", we recommend the Movie470 to the User101 because this movie outperforms all the other movies with the highest score of 0.26.
### Task
The task is then reduced to finding the movie with the highest score for each user.
### Scoring
1. See a simple way to explain the [**basic idea**](../master/docs/Basic%20Idea.pdf) for scoring.
2. [**A quick view**](../master/docs/Scoring%20Schema.pdf) of how to get each movie's score for a user.
