from fastapi import FastAPI, HTTPException
import random

app = FastAPI()

# Mock emotion classification endpoint
@app.post("/classify/emotion")
async def classify_emotion(data: dict):
    text = data.get("text", "")
    if not text:
        raise HTTPException(status_code=400, detail="Text is required")
    # Simulate occasional failure
    if random.random() < 0.2:  # 20% chance of failure
        raise HTTPException(status_code=500, detail="Internal server error")
    emotions = ["Happy", "Sad", "Angry"]
    return {"text": text, "emotion": random.choice(emotions)}

# Mock speech analysis endpoint
@app.post("/analyze/speech")
async def analyze_speech(data: dict):
    text = data.get("text", "")
    if not text:
        raise HTTPException(status_code=400, detail="Text is required")
    return {"text": text, "speech_type": "Declarative"}

# Mock vision prediction endpoint
@app.post("/predict/vision")
async def predict_vision(data: dict):
    image_id = data.get("image_id", "")
    if not image_id:
        raise HTTPException(status_code=400, detail="Image ID is required")
    return {"image_id": image_id, "object": "Car"}

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8001)  # Use port 8001 to avoid conflict with Day 4