# Movie-Recommendation-System

## Command
Upload **RecommendSystem-0.0.6.jar** and **/input** on Hadoop 2.7.3. Run it with this command:

    hadoop jar RecommendSystem-0.0.6.jar org/lingbo/hadoop/RecommendSystem /input /output1 /output2 /output3 /output4 /output5 /output6

Final recommendation result is in /output6. Others are intermediate outputs.
## Input & Output
1. This system takes a plain text file as its [**input file**](../master/input/input.txt). See the [**input format**](../master/docs/Input.pdf) here.
2. This system emits a plain text file as its [**output file**](../master/output/output6/part-r-00000). See the [**output format**](../master/docs/Output.pdf) here.
## Task
The task is then reduced to finding the movie with the highest score for each user.
## Scoring
1. See a simple way to explain the [**basic idea**](../master/docs/Basic%20Idea.pdf) for scoring.
2. See the [**main steps**](../master/docs/Main%20Steps.pdf) needed for the whole procedure:
    1)  Find each user's watch-list according to input.txt. [**(See output)**](../master/output/output1/part-r-00000)
    2)  Build movie-movie [**correlation matrix**](../master/docs/Movie%20Movie%20Correlation%20Matrix.pdf). [**(See output)**](../master/output/output2/part-r-00000)
    3)  Prepare for normalization. Find each movie the denominator. [**(See output)**](../master/output/output3/part-r-00000)
    4)  Prepare for matrix multiplication. Rotate movie-movie correlation matrix. [**(See output)**](../master/output/output4/part-r-00000)
    5)  Rule out watched movies. Do [**normalization**](../master/docs/Normalization.pdf) and [**calculate scores**](../master/docs/Scoring%20Schema.pdf) at the same time. [**(See output)**](../master/output/output5/part-r-00000)
    6)  Find the movie of the highest score for each user. [**(See output)**](../master/output/output6/part-r-00000)
