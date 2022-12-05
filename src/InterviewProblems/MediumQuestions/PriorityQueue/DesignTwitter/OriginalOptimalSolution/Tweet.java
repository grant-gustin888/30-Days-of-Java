package InterviewProblems.MediumQuestions.PriorityQueue.DesignTwitter.OriginalOptimalSolution;

class Tweet {

    int userID;
    int tweetID;
    int timestamp;

    public Tweet(int userID, int tweetID, int timestamp) {
        this.userID = userID;
        this.tweetID = tweetID;
        this.timestamp = timestamp;
        // My original solution didn't have a timestamp, so my news feed didn't necessary
        // sort the tweets by most recent first. Perhaps I could have used a linked list instead
        // and take advantage of the natural ordering of the linked list indices.
    }
}
