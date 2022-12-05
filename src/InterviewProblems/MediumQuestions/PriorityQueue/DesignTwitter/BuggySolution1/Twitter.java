package InterviewProblems.MediumQuestions.PriorityQueue.DesignTwitter.BuggySolution1;

import java.util.*;

public class Twitter {

    public static void main(String[] args) {
        Twitter twitter1 = new Twitter();
        twitter1.postTweet(1, 5);  // User 1 posts a new tweet (id = 5).
        System.out.println(twitter1.getNewsFeed(1));  // [5]
        // User 1's news feed should return a list with 1 tweet id -> [5].
        twitter1.follow(1, 2);  // User 1 follows user 2.
        twitter1.postTweet(2, 6);  // User 2 posts a new tweet (id = 6).
        System.out.println(twitter1.getNewsFeed(1));  // [6, 5]
        // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
        // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        twitter1.unfollow(1, 2);  // User 1 unfollows user 2.
        System.out.println(twitter1.getNewsFeed(1));  // [5]
        // User 1's news feed should return a list with 1 tweet id -> [5],
        // since user 1 is no longer following user 2.

        Twitter twitter2 = new Twitter();
        twitter2.postTweet(1, 5);
        twitter2.postTweet(1, 3);
        System.out.println(twitter2.getNewsFeed(1));  // [3, 5]
        // User 1's news feed should return a list with 2 tweet ids -> [3, 5].
        // Tweet id 3 should precede tweet id 5 because it is posted after tweet id 5.

        Twitter twitter3 = new Twitter();
        twitter3.postTweet(1, 5);
        twitter3.postTweet(2, 3);
        twitter3.postTweet(1, 101);
        twitter3.postTweet(2, 13);
        twitter3.postTweet(2, 10);
        twitter3.postTweet(1, 2);
        twitter3.postTweet(1, 94);
        twitter3.postTweet(2, 505);
        twitter3.postTweet(1, 333);
        twitter3.postTweet(2, 22);
        twitter3.postTweet(1, 11);
        twitter3.postTweet(1, 205);
        twitter3.postTweet(2, 203);
        twitter3.postTweet(1, 201);
        twitter3.postTweet(2, 213);
        twitter3.postTweet(1, 200);
        twitter3.postTweet(2, 202);
        twitter3.postTweet(1, 204);
        twitter3.postTweet(2, 208);
        twitter3.postTweet(2, 233);
        twitter3.postTweet(1, 222);
        twitter3.postTweet(2, 211);
        System.out.println(twitter3.getNewsFeed(1));  // [222, 204, 200, 201, 205, 11, 333, 94, 2, 101]

        twitter3.follow(1, 2);
        System.out.println(twitter3.getNewsFeed(1));  // [211, 222, 233, 208, 204, 202, 200, 213, 201, 203]

        twitter3.unfollow(1, 2);
        System.out.println(twitter3.getNewsFeed(1));  // [222, 204, 200, 201, 205, 11, 333, 94, 2, 101]
    }

    /**
     * Design a simplified version of Twitter where users can post tweets,
     * follow/unfollow another user, and is able to see the 10 most recent tweets in the
     * user's news feed.
     *
     * Implement the Twitter class.
     *
     * Preconditions:
     * - 1 <= userId, followerId, followeeId <= 500.
     * - 0 <= tweetId <= 10 ^ 4.
     * - All the tweets have unique IDs.
     * - At most 3 * 10 ^ 4 calls will be made to postTweet, getNewsFeed, follow, and unfollow.
     */
    Map<Integer, Set<Integer>> userIDsToFollowees;
    PriorityQueue<Tweet> newsFeed;  // a max heap of tweet IDs, and the user ID that posted it.
    final int NUM_MOST_RECENT_TWEETS = 10;
    int currentTimestamp = 1;

    /**
     * Initializes your twitter object.
     */
    // Let n = the number of tweets users post.
    // Let m = the number of users.
    //
    // Time: O(1)
    // --> O(1) to initialize the userIDsToFollowees map.
    // --> O(1) to initialize the newsFeed priority queue.
    // Space: O(max(m, n))
    // --> O(m) space for allocating space for users in the hash map.
    // --> O(n) space for allocating space for tweets in news feed.
    public Twitter() {
        userIDsToFollowees = new HashMap<>();
        newsFeed = new PriorityQueue<>((tweet1, tweet2) -> {
            int tweet1Timestamp = tweet1.tweetID;
            int tweet2Timestamp = tweet2.tweetID;
            return tweet2Timestamp - tweet1Timestamp;  // return highest tweet timestamp first...
        });
    }

    /**
     * The user with ID followerId started following the user with ID followeeId.
     */
    // Time: O(1)
    // --> O(1) to create a new set of followees if the followerID doesn't exist.
    // --> O(1) to add the followee to the set of followees.
    // Space: O(1)
    // --> This algorithm doesn't need any auxiliary data structures, except creating an empty
    // hash set of followees to the new userID in the userIDsToFollowees hash map.
    public void follow(int followerId, int followeeId) {
        if (!userIDsToFollowees.containsKey(followerId)) {
            Set<Integer> followees = new HashSet<>();
            followees.add(followerId);  // add yourself as a followee...
            userIDsToFollowees.put(followerId, followees);
        }
        // now userID followerID follows userID followeeId.
        userIDsToFollowees.get(followerId).add(followeeId);
    }

    /**
     * The user with ID followerId started unfollowing the user with ID followeeId.
     */
    // Time: O(1)
    // --> O(1) to check if the followerID exists, and if the follower-followee relationship exists.
    // --> O(1) to remove the followee from the set of followees.
    // Space: O(1)
    // --> This algorithm doesn't need any auxiliary data structures.
    public void unfollow(int followerId, int followeeId) {
        // Q1: Can I assume that it is impossible to unfollow a followee that a user
        // doesn't already follow?
        // A1: Yes.
        if (userIDsToFollowees.containsKey(followerId) && follows(followerId, followeeId)) {
            userIDsToFollowees.get(followerId).remove(followeeId);
        }
    }

    private boolean follows(int followerId, int followeeId) {
        return userIDsToFollowees.get(followerId).contains(followeeId);
    }

    /**
     * Composes a new tweet with ID tweetId by the user userId.
     * Each call to this function will be made with a unique tweetId.
     */
    // Time: O(1)
    // --> O(1) to add the tweet to the news feed.
    // Space: O(1)
    // --> This algorithm doesn't need any auxiliary data structures, except creating an empty
    // hash set of followees to the new userID in the userIDsToFollowees hash map.
    public void postTweet(int userId, int tweetId) {
        if (!userIDsToFollowees.containsKey(userId)) {
            Set<Integer> followees = new HashSet<>();
            userIDsToFollowees.put(userId, followees);
        }

        Tweet newTweet = new Tweet(userId, tweetId, currentTimestamp);
        newsFeed.add(newTweet);
        currentTimestamp++;
    }

    /**
     * Retrieves the 10 most recent tweet IDs in the user's news feed.
     * Each item in the news feed must be posted by users who the user followed or by
     * the user themselves. Tweets must be ordered from most recent to least recent.
     */
    // Let n = the number of tweets users post.
    //
    // Time: O(n log n)
    // --> We'll need up to n O(log n) extract max operations to get the 10 most recent tweets
    // if none of the tweets are posted by this userId or any of their followees.
    // Space: O(n)
    // --> O(1) for the top 10 tweets.
    // --> O(n) in case none of the tweets are posted by this userId or any of their followees.
    public List<Integer> getNewsFeed(int userId) {
        // Q2: If I have less than 10 tweetIDs in the user's news feed, can I just
        // return all the tweets in the news feed?
        // A2: Yes.
        List<Integer> topTweets = new ArrayList<>();

        // temporarily take out the top 10 tweets from the news feed.
        List<Tweet> tweetsExtractedSoFar = new ArrayList<>();
        while (!newsFeed.isEmpty() && topTweets.size() < NUM_MOST_RECENT_TWEETS) {
            Tweet currentTweet = newsFeed.poll();
            tweetsExtractedSoFar.add(currentTweet);

            Set<Integer> followees = userIDsToFollowees.get(userId);
            if (currentTweet.userID == userId || followees.contains(currentTweet.userID)) {
                topTweets.add(currentTweet.tweetID);
            }
        }

        // Put the temporary tweets back in the priority queue.
        for (Tweet tweet : tweetsExtractedSoFar) {
            newsFeed.add(tweet);
        }

        return topTweets;
    }
}
