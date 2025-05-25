def mock_resnet_classify(image_path):

    if "dog" in image_path.lower():
        return "Dog"
    elif "cat" in image_path.lower():
        return "Cat"
    else:
        return "Unknown"

image_path = "sample_dog.jpg"
result = mock_resnet_classify(image_path)
print(f"Image: {image_path} → Classification: {result}")
