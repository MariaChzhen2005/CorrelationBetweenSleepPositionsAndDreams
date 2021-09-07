import math
import pandas as pd

data = pd.read_csv('/Users/mariachzhen/Desktop/Files/DB-Exercises/Sleep.csv') # local file; same dataset.

rightPos = list([9,18,19,23])   # list of positions on the right
leftPos = list([3,6,11,21])     # list of positions on the left

wakePos = data['wakePos']
vividness = data['vividness']
age = data['age']

vividnessRightData = data.loc[wakePos.isin(rightPos)]['vividness']
standardDevR = vividnessRightData.std()
vividnessRightDataLength = len(list(vividnessRightData.index))
sqrtSampleR = math.sqrt(vividnessRightDataLength)
rightPosCount = 0
vividRCount = 0
vividRMean = 0

vividnessLeftData = data.loc[wakePos.isin(leftPos)]['vividness']
standardDevL = vividnessLeftData.std()
vividnessLeftDataLength = len(list(vividnessLeftData.index))
sqrtSampleL = math.sqrt(vividnessLeftDataLength)
leftPosCount = 0
vividLCount = 0
vividLMean = 0

for i in data.index:
    if data.iloc[i]['wakePos'] in rightPos and data.iloc[i]['wakePos']!=0:
        rightPosCount+=1
        vividRCount += data.iloc[i]['vividness']
        
for i in data.index:
    if data.iloc[i]['wakePos'] in leftPos and data.iloc[i]['wakePos']!=0:
        leftPosCount+=1
        vividLCount += data.iloc[i]['vividness']
        
# means
vividRMean = vividRCount / rightPosCount
vividLMean = vividLCount / leftPosCount

"""
z-score of each vividity on the right side
for i in data.index:
    if data.iloc[i]['wakePos'] in rightPos and data.iloc[i]['wakePos']!=0:
        z = (data.iloc[i]['vividness']-vividRMean)/sqrtSampleR
"""

# confidence intervals
LCIup = str(vividLMean + (z*standardDevL/sqrtSampleL))
LCIdown = str(vividLMean - (z*standardDevL/sqrtSampleL))
RCIup = str(vividRMean + (z*standardDevR/sqrtSampleR))
RCIdown = str(vividRMean - (z*standardDevR/sqrtSampleR))

"""
z-test and calculate p-value (1 tailed)
The null hypothesis - vividRMean = vividness.mean()
The alt hypothesis - vividRMean > vividness.mean() - ideally

"""
print("Right side")
print("The z-value is "+str((vividRMean-vividness.mean())/(standardDevR/sqrtSampleR)))
print("We fail to reject the null hypothesis. Causation not established for right side - p>0.05")
print('95% confidence interval: [' + RCIup +'; ' + RCIdown + ']')


print("\nLeft side")
print("The z-value is "+str((vividLMean-vividness.mean())/(standardDevL/sqrtSampleL)))
print("We fail to reject the null hypothesis. Causation not established for left side - p>0.05")
print('95% confidence interval: [' + LCIup +'; ' + LCIdown + ']')
