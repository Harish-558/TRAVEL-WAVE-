import requests
import json

url = "https://integrate.api.nvidia.com/v1/chat/completions"
headers = {
    "Authorization": "Bearer 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",
    "Content-Type": "application/json"
}
data = {
    "model": "meta/llama3-8b-instruct",
    "messages": [{"role": "user", "content": "hi"}]
}
response = requests.post(url, headers=headers, json=data)
print(response.status_code)
print(response.text)
