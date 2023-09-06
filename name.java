<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="All Test Suite">
    <groups>
        <run>
            <include name="integration"/>
            <include name="smoke"/>
        </run>
    </groups>
    <test thread-count="1" parallel="tests" name="C:/Users/ASHISH/Desktop/com.ecommerce.tyss" preserve-order="true">
        <classes>
            <class name="com.practice.TestNgOrder"/>
            <class name="com.practice.TestNgPriority">
            <methods>
                <include name="createSubcategory"/>
            </methods>
            </class>
        </classes>
    </test>
</suite>