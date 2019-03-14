package com.huankong.fictionalfiction.bean.biquege.details;

public class BiQueGeDetailsBookVote {
    private long BookId;
    private int TotalScore;
    private int VoterCount;
    private double Score;

    public long getBookId() {
        return BookId;
    }

    public void setBookId(long bookId) {
        BookId = bookId;
    }

    public int getTotalScore() {
        return TotalScore;
    }

    public void setTotalScore(int totalScore) {
        TotalScore = totalScore;
    }

    public int getVoterCount() {
        return VoterCount;
    }

    public void setVoterCount(int voterCount) {
        VoterCount = voterCount;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double score) {
        Score = score;
    }
}
