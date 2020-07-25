
#include <iostream>
#include <dirent.h>
#include <sys/stat.h>

#include "equalizer/Files.h"

bool Files::endsWith(const std::string &mainStr, const std::string &toMatch)
{
	if(mainStr.size() >= toMatch.size() &&
			mainStr.compare(mainStr.size() - toMatch.size(), toMatch.size(), toMatch) == 0)
			return true;
		else
			return false;
}


int Files::getDirFiles (std::string dir, std::string filesExtention, std::vector<std::string> &files){
    DIR *dp;
    struct dirent *dirp;
    if((dp  = opendir(dir.c_str())) == NULL) {
        throw "Directory " + dir + " not found";
    }

    while ((dirp = readdir(dp)) != NULL) {
    	if(filesExtention != ""){
            if(Files::endsWith(dirp->d_name, filesExtention))
                if(!(dirp->d_name == std::string(".") || dirp->d_name == std::string("..")))
                    files.push_back(std::string(dirp->d_name));
        }else{
             if(!(dirp->d_name == std::string(".") || dirp->d_name == std::string("..")))
                    files.push_back(std::string(dirp->d_name));
        }
            
    }
    closedir(dp);
    return 0;
}


std::string Files::getFileName(std::string path){
    char slash = '/';
    char dot = '.';
#ifdef _WIN32
    slash = '\\';
#endif
    size_t last;
    last = path.find_last_of(slash);
    std::string name;
    if(last != std::string::npos){
        name = path.substr(last+1);
    }else{
        name = path;
    }

    last = name.find_last_of(dot);
    if(last != std::string::npos){
        name = name.substr(0, last);
    }
    return name;
    
}

bool Files::is_file(const char* path) {
    struct stat buf;
    stat(path, &buf);
    return S_ISREG(buf.st_mode);
}

char Files::slash(){
    char s = '/';
#ifdef _WIN32
    s = '\\';
#endif
    return s;
}

void Files::initializeOutputDires(std::string dir){
	struct stat info;
	if(stat(dir.c_str(), &info) != 0){
		int check = mkdir(dir.c_str(), 0777);
		if(check == -1)
			std::cerr << "cannot create Direcory" << std::endl;
		else
			std::cout << "Directory " << dir << "created successfully" << std::endl;	
	}
}