# Movie-Recommendation-System

This project was done without using Github.
### Input
This system takes a plain text file as it's input. See the [**input file**](../master/input.txt) here. The file contains a great number of lines where each line has three fields separated by commas. A line stands for one record: **"userID,movieID,rating"**. For example, "424,786,1.3" means that the user with ID 424 watched the movie with ID 786, and rated it 1.3 stars out of 5 stars.




Run RecommendSystem-0.0.6.jar on hadoop with this command:
    hadoop jar RecommendSystem-0.0.6.jar org/lingbo/hadoop/RecommendSystem /input /output1 /output2 /output3 /output4 /output5 /output6
where /output1 to /output6 are the output directories. Final recommendation result is in /output6.
