FROM python:3.10
WORKDIR /code
COPY ./src/main/chatbot/requirements.txt /code/requirements.txt
RUN pip install --no-cache-dir --upgrade -r /code/requirements.txt
COPY ./src/main/chatbot/app /code/app
CMD ["uvicorn", "app.main:app", "--reload"]