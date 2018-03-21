# Movie-Recommendation-System

This project was done without using Github.
### Command
Upload **RecommendSystem-0.0.6.jar** and **/input** on Hadoop 2.7.3. Run it with this command:

    hadoop jar RecommendSystem-0.0.6.jar org/lingbo/hadoop/RecommendSystem /input /output1 /output2 /output3 /output4 /output5 /output6

Final recommendation result is in /output6.
### Input
This system takes a plain text file as its [**input file**](../master/input/input.txt). See the [**input format**](../master/docs/Input.pdf) here.
### Output
1. This system emits a plain text file as its output. See the [**output file**](../master/output/output6/part-r-00000) here.
2. Each line contains a recommendation for a user in the format of "_userID    recommended_movieID:score_".
3. E.g. "101	470:0.26", we recommend the Movie470 to the User101 because this movie outperforms all the other movies with the highest score of 0.26.
### Task
The task is then reduced to finding the movie with the highest score for each user.
### Scoring
1. See a simple way to explain the [**basic idea**](../master/docs/Basic%20Idea.pdf) for scoring.
2. [**A quick view**](../master/docs/Scoring%20Schema.pdf) of how to get each movie's score for a user.
3. Here are the [**main steps**](../master/docs/Main%20Steps.pdf) needed for the whole procedure.
    1) 
