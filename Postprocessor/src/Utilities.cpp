#include "equalizer/Utilities.hpp"

int getPercent(int sub, int max){
	double rat = sub / (double) max;
	return (rat * 100);
}