from textblob import TextBlob

def analyze_sentiment(comment):
    analysis = TextBlob(comment)
    polarity = analysis.sentiment.polarity
    if polarity > 0:
        return "Positive"
    elif polarity < 0:
        return "Negative"
    else:
        return "Neutral"

with open("comments.txt", "r", encoding="utf-8") as f:
    comments = f.readlines()

print("Sentiment Analysis of YouTube Shorts Comments:\n")
for i, comment in enumerate(comments, 1):
    sentiment = analyze_sentiment(comment.strip())
    print(f"{i}. {sentiment:>8} → {comment.strip()}")
