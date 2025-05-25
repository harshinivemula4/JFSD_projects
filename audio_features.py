import librosa
import numpy as np

def extract_audio_features(audio_path):

    y, sr = librosa.load(audio_path)

    mfccs = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=13)

    mfccs_mean = np.mean(mfccs, axis=1)

    return mfccs_mean

try:
    audio_path = "sample_audio.wav"
    features = extract_audio_features(audio_path)
    print(f"Audio: {audio_path} → MFCC Means: {features}")
except FileNotFoundError:
    print("Audio file not found. Mocking audio features...")

    mock_features = np.array([-300.5, 120.3, -50.2, 30.1, -10.5, 5.2, -2.3, 1.8, -1.2, 0.9, -0.5, 0.3, -0.1])
    print(f"Audio: {audio_path} → Mock MFCC Means: {mock_features}")


