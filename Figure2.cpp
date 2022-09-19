#include <iostream>
using namespace std;

class IMovable {    // interface
public:
    virtual void move(int dx, int dy) = 0;	// abstract operation
};

class Figure : public IMovable {  // implements IMovable, abstract class
private:
    int color;
public:
    void setColor(int c) { color = c; }

    virtual void draw() = 0;		// abstract operation
    Figure(int c) {
        color = c;
        cout << "Figure::Figure()" << endl;
    }
    virtual ~Figure() {  //dynamic binding
        cout << "Figure::~Figure()\n";
    }
};

class Point : public IMovable {
    int x, y;
public:
    void move(int dx, int dy) {
        x += dx;
        y += dy;
        cout << "Point::move()\n";
    }
    Point(int xp, int yp) {
        x = xp;
        y = yp;
        cout << "Point::Point()\n";
    }
    ~Point() { cout << "Point::~Point()\n"; }
};

class Circle : public Figure {
    Point center;
    int radius;
public:
    void move(int dx, int dy) {
        center.move(dx, dy);
        cout << "Circle::move()\n";
    }
    void draw() { cout << "Circle::draw()\n"; }

    void setRadius(int r) { radius = r; }

    Circle(int x, int y, int r, int c) : Figure(c), center(x, y) {
        radius = r;
        cout << "Circle::Circle()\n";
    }
    ~Circle() { cout << "Circle::~Circle()\n"; }
};

class Rectangle : public Figure {
    Point topleft;
    int width, height;
public:
    void move(int dx, int dy) {
        topleft.move(dx, dy);
        cout << "Rectangle::move()\n";
    }
    void draw() { cout << "Rectangle::draw()\n"; }

    void setWidth(int w) { width = w; }
    void setHeight(int h) { height = h; }

    Rectangle(int x, int y, int w, int h, int c) : Figure(c), topleft(x, y) {
        width = w;	height = h;
        cout << "Rectangle::Rectangle()\n";
    }
    ~Rectangle() { cout << "Rectangle::~Rectangle()\n"; }
};

int main() {
    //Figure a(1);

    Figure* f[10];
    int num = 4;
    f[0] = new Circle(100, 100, 50, 2);
    f[1] = new Circle(200, 200, 30, 2);
    f[2] = new Rectangle(10, 10, 50, 40, 3);
    f[3] = new Rectangle(100, 100, 70, 30, 4);

    int dx = 10, dy = 20, i;
    for (i = 0; i < num; i++)
        f[i]->move(dx, dy);		// polymorphism
    for (i = 0; i < num; i++)
        f[i]->draw(); 		// polymorphism

    for (i = 0; i < num; i++)
        delete f[i];
}
