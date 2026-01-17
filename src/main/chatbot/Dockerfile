FROM python:3.10
WORKDIR /code
COPY ./src/main/chatbot/requirements.txt /code/requirements.txt
RUN pip install --no-cache-dir --upgrade -r /code/requirements.txt

# Copia o config.properties só se ele existir
RUN if [ -f "./src/main/resources/config.properties" ]; then \
        cp ./src/main/resources/config.properties /resources/config.properties; \
    else \
        echo "config.properties não encontrado, seguindo sem ele"; \
    fi

COPY ./src/main/chatbot/app /code/app
CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "8000", "--reload"]