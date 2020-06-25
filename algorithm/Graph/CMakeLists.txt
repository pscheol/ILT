cmake_minimum_required(VERSION 3.8)
project(untitled2)

set(CMAKE_C_STANDARD 11)

set(SOURCE_FILES main.c GraphTraversal.c GraphTraversal.h Graph.c Graph.h LinkedListQueue.c LinkedListQueue.h)
add_executable(untitled2 ${SOURCE_FILES})