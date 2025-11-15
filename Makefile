SRC_DIR := src
BUILD_DIR := out

MAIN_CLASS ?= dev.typegaro.drimu.core.Main 

SOURCES := $(shell find $(SRC_DIR) -name '*.java')

.PHONY: all
all: build

$(BUILD_DIR)/classes.stamp: $(SOURCES)
	@mkdir -p $(BUILD_DIR)
	javac -d $(BUILD_DIR) $(SOURCES)
	@touch $@

.PHONY: build
build: $(BUILD_DIR)/classes.stamp
	@echo "Build completata in $(BUILD_DIR)"

.PHONY: run
run: build
	java -cp $(BUILD_DIR) $(MAIN_CLASS)

.PHONY: jar
jar: build
	jar --create --file app.jar --main-class $(MAIN_CLASS) -C $(BUILD_DIR) .
	@echo "Creato app.jar (java -jar app.jar)"

.PHONY: clean
clean:
	rm -rf $(BUILD_DIR) app.jar
	@echo "Pulizia completata"
