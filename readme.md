Для того, щоб у вас все працювало.
треба налаштувати AWS CLI
щоб з нього можна було робити запити у AWS polly робити запит
наприклад такий 
"aws polly synthesize-speech ^
--output-format mp3 ^
--voice-id Joanna ^
--text "processed" ^
processed.mp3"

без aws polly можна юзати другий інтерфейс