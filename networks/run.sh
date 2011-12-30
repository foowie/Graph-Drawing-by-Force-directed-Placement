#!/bin/bash
java -jar ../dist/FruchtermanReingold.jar -i=circle.in -t=1000 
java -jar ../dist/FruchtermanReingold.jar -i=cube.in -t=1000 
java -jar ../dist/FruchtermanReingold.jar -i=random.in -t=1000 
java -jar ../dist/FruchtermanReingold.jar -i=social1Inter_st.in -t=1000 
java -jar ../dist/FruchtermanReingold.jar -i=yeastInter_st.in -n=no -w=2000 -h=2000 -t=200 
java -jar ../dist/FruchtermanReingold.jar -i=celegans_n306.in -n=no -w=2000 -h=2000 -t=200 
java -jar ../dist/FruchtermanReingold.jar -i=USairport500.in -n=no -w=2000 -h=2000 -t=200 
