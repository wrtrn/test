#!/bin/bash

IMAGE_NAME=nbank-tests
TEST_PROFILE=${1:-api}
TIMESTAMP=$(date +"%Y%m%d_%H%M")
TEST_OUTPUT_DIR=./test-output/$TIMESTAMP

# Создаем папки для отчетов
mkdir -p "$TEST_OUTPUT_DIR/logs"
mkdir -p "$TEST_OUTPUT_DIR/results"
mkdir -p "$TEST_OUTPUT_DIR/report"

# Функция для очистки при выходе
cleanup() {
    echo ""
    echo ">>> Остановка тестового окружения (Docker Compose)..."
    docker compose down
    echo ">>> Окружение остановлено."
}

# Перехватываем выход из скрипта, чтобы всегда гасить контейнеры
trap cleanup EXIT

echo ">>> Подготовка тестового окружения..."

if command -v jq &> /dev/null; then
   echo ">>> Проверка обновлений браузеров..."
   json_file="./config/browsers.json"
   images=$(jq -r '.. | objects | select(.image) | .image' "$json_file")
   for image in $images; do docker pull "$image"; done
fi

echo ">>> Запуск Docker Compose (в фоновом режиме)..."
docker compose up -d --wait

echo ">>> Сборка образа с тестами ($IMAGE_NAME)..."
docker build -t $IMAGE_NAME .

echo ">>> Запуск тестов (Профиль: $TEST_PROFILE)..."

docker run --rm \
  --network host \
  -v "$TEST_OUTPUT_DIR/logs":/app/logs \
  -v "$TEST_OUTPUT_DIR/results":/app/target/surefire-reports \
  -v "$TEST_OUTPUT_DIR/report":/app/target/site \
  -e TEST_PROFILE="$TEST_PROFILE" \
  -e APIBASEURL=http://localhost:4111 \
  -e UIBASEURL=http://localhost:80 \
  -e SELENOID_URL=http://localhost:4444 \
  $IMAGE_NAME

echo ">>> Тесты завершены"
echo "Лог файл: $TEST_OUTPUT_DIR/logs/run.log"
echo "Результаты: $TEST_OUTPUT_DIR/results"