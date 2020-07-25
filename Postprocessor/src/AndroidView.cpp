
#include<string>
#include<equalizer/AndroidView.hpp>

const std::string AndroidView::VIEW_KEY = "view";
const std::string AndroidView::ID_KEY = "id";
const std::string AndroidView::Start_PERCENT_KEY = "start_percent";
const std::string AndroidView::TOP_PERCENT_KEY = "top_percent";
const std::string AndroidView::END_PERCENT_KEY = "end_percent";
const std::string AndroidView::BOTTOM_PERCENT_KEY = "bottom_percent";


AndroidView::AndroidView(){}
AndroidView::AndroidView(std::string view, std::string id, double start_percent, 
double top_percent, double end_percent, double bottom_percent){
    this->view = view;
    this->id = id;
    this->start_percent = start_percent;
    this->top_percent = top_percent;
    this->end_percent = end_percent;
    this->bottom_percent = bottom_percent;
}

void AndroidView::setView(std::string view){
    this->view = view;
}
void AndroidView::setId(std::string id){
    this->id = id;
}
void AndroidView::setStartPercent(double percent){
    this->start_percent = percent;
}
void AndroidView::setTopPercent(double percent){
    this->top_percent = percent;
}
void AndroidView::setEndPercent(double percent){
    this->end_percent = percent;
}
void AndroidView::setBottomPercent(double percent){
    this->bottom_percent = percent;
}

std::string AndroidView::getView()const{
    return this->view;
}
std::string AndroidView::getId()const{
    return this->id;
}
double AndroidView::getStartPercent()const{
    return this->start_percent;
}
double AndroidView::getTopPercent()const{
    return this->top_percent;
}
double AndroidView::getEndPercent()const{
    return this->end_percent;
}
double AndroidView::getBottomPercent()const{
    return this->bottom_percent;
}