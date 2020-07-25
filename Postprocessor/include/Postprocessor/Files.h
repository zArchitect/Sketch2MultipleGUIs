#ifndef FILES_OPERATIONS_
#define FILES_OPERATIONS_

#include <string>
#include <vector>

class Files{
private:
    Files();
    ~Files();
    //check string if end with specific string
    static bool endsWith(const std::string &mainStr, const std::string &toMatch);

public:
    //get data files from thire directories
    static int getDirFiles (std::string dir, std::string filesExtention, std::vector<std::string> &files);
    //get the name of input file
    static std::string getFileName(std::string path);
    //check if path is file or not 
    static bool is_file(const char* path);
    static void initializeOutputDires(std::string dir);
    static char slash();

};

#endif