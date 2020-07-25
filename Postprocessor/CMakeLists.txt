cmake_minimum_required(VERSION 3.1)

project(equalizer)

file(GLOB _SRC "src/*.cpp")

set(EXECUTABLE_OUTPUT_PATH "${PROJECT_SOURCE_DIR}/bin/")

set(CMAKE_CXX_FLAGS "-Wall -O2 -g")

add_executable(equalizer ${_SRC})

target_include_directories(equalizer PRIVATE "${PROJECT_SOURCE_DIR}/include")