# Required pip installs: pytesseract, gtts

import pytesseract
from PIL import Image, ImageEnhance, ImageFilter
from gtts import gTTS

def imageToText(png_path):
    img = Image.open(png_path).convert('RGBA')
    pytesseract.pytesseract.tesseract_cmd = 'C:\\Program Files (x86)\\Tesseract-OCR\\tesseract.exe'
    text = pytesseract.image_to_string(img)
    print(text)
    return text

def textToSpeech(text_to_read, language, name_to_save):
    tts = gTTS(text_to_read, language)
    tts.save(name_to_save)

def imageToSpeech(img_path, language, audio_file_name):
    textToSpeech(imageToText(img_path), language, audio_file_name)

def imageListToSpeech(img_path_list, language, audio_file_name_list):
    i = 0
    for img_path in img_path_list:
        imageToSpeech(img_path, language, audio_file_name_list[i])
        if (i < len(audio_file_name_list)):
            i += 1

def main():
	imageToSpeech('book_test_1.jpg', 'en', 'book_audio_test_1.mp3')
