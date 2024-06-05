
2024-04-16 과제 2번 간단한 게시판 작성(Rest API를 사용)

1. 게시판 등록 -> http://localhost:8080/api/boards/register
![image](https://github.com/DongHyeonka/Backend/assets/144816658/23da4df4-ab41-48bc-990b-e52babf8d022)

2. 게시판 조회 ->http://localhost:8080/api/boards/get/1
![image](https://github.com/DongHyeonka/Backend/assets/144816658/a2729788-1764-43c8-8d3e-b9639c9a3749)

3. 게시판 리스트 조회 -> http://localhost:8080/api/boards/list
![image](https://github.com/DongHyeonka/Backend/assets/144816658/2f235775-a710-464f-add8-5c613edccbc6)

4. 게시판 리스트 좋아요순 조회 -> http://localhost:8080/api/boards/updateList
![image](https://github.com/DongHyeonka/Backend/assets/144816658/9e391343-3887-4b48-b74d-edc93740edb5)
내림차순 정렬

![image](https://github.com/DongHyeonka/Backend/assets/144816658/70572c96-e7e4-4649-8370-73fd02eb37f2)
오름차순 정렬


6. 게시판 좋아요 -> http://localhost:8080/api/boards/like/1
![image](https://github.com/DongHyeonka/Backend/assets/144816658/07ffafa2-8499-4825-984c-c03b8542cacb)
![image](https://github.com/DongHyeonka/Backend/assets/144816658/d0a20e29-30c0-4b27-8981-4e71c5343c1d)
=> 다시 조회하면 좋아요 1 카운트 된 모습

7. 게시판 삭제 -> http://localhost:8080/api/boards/delete/1
![image](https://github.com/DongHyeonka/Backend/assets/144816658/6fa18225-dbeb-454a-8b2c-cf84f461c698)
![image](https://github.com/DongHyeonka/Backend/assets/144816658/f0d0017b-c5f1-4d41-8fab-18bacb30a4bb)
=> 다시 조회한 결과 없음
