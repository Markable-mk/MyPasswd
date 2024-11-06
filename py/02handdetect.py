import cv2
import mediapipe as mp

# 笔记本使用0
cap = cv2.VideoCapture(0)
mpHands=mp.solutions.hands
hands=mpHands.Hands(False,2,1,0.5,0.5);
# 绘制工具
mpDraw=mp.solutions.drawing_utils

while True:
    # ret 表示是否成功捕获到图形
    ret,img = cap.read()
    if ret:
        imgRGB=cv2.cvtColor(img,cv2.COLOR_BGR2RGB)
        result =hands.process(imgRGB)
        # print(result.multi_hand_landmarks)
        if result.multi_hand_landmarks:
            for handLms in result.multi_hand_landmarks:
                mpDraw.draw_landmarks(img,handLms,mpHands.HAND_CONNECTIONS)
        # img 为 左上角的名称
        cv2.imshow('img',img)
    #  监听按下q键  ord('q') 返回 'q' 的 ASCII 码
    if cv2.waitKey(1) == ord('q'):
        break