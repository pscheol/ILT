# Messaging System

Messaging System은 하나의 프로그램에서 다른 프로그램으로 데이터를 전달할 수 있도록 돕는 시스템이다.

> #### Messaging System의 기본 원칙

 - Loose Coupling(느슨한 연계) : Application 상호간에 의존성을 최소화 하는 것으로 한쪽에서 변경이 발생할 경우 다른 프로그램은 영향을 받지 않는 것.
 - Common Interface(공용 인터페이스 정의) : Application간에 데이터를 교환하기 위해 공용으로 규정된 데이터 형식을 정의한다.
 - Latency(응답속도) :메시지 전송부터 수신까지의 소요시간을 말한다.
 - reliability(신뢰성) : 일시적인 가용성 문제가 발생해도 정보를 교환하는 Application에는 영향을 주지 않는다.


> #### 기본 개념

- Message Queue : Application간 송신과 수신을 위한 연결고리 역할을 하며, source로부터 메시지를 수신받으면 그것을 다시 target에게 보낸다.

- Message(Data Packet) : Message는 네트워크를 통해 Message Queue로 보내는 단위 Data Packet이다. 송신프로그램은 데이터를 더 작은 Data Packet단위로 쪼개고, Protocol과 Header 기반으로 데이터를 Wrapping하여 Message Queue에 보낸다. 그리고 수신 프로그램은 Message Wrapper에서 데이터를 추출한다.

- Sender(Producer) : 특정 목적지로 보내는 Data Source로 Message Queue의 End-Point에 연결을 생성하고 Common Interface에 맞게 더 작은 패킷 단위로 데이터를 전송한다. 시스템 유형에 따라 Batch or 하나씩 보낼 수 있다.

- Receiver(Consumer) : 전송 받은 메시지를 수신받는 것. Message Queue에서 데이터를 가져오거나 지속적인 연결을 통해 Message Queue에서 데이터를 받는다.

- Data Transfer Protocol(데이터 전송 프로토콜) : Application간에 메시지 교환을 통제하는 규칙을 결정한다.(Kafka는 TCP기반 Binary Protocal을 사용) Protocol 종류로는 AMQP(Advanced Message Queueing Protocol), STOMP(Streaming Text Oriented Message Protocol), MQTT(Message Queue Telemetry Transport), HTTP(Hypertext Transfer Protocol)등이 있다.

- 전송모드 : 데이터가 소스프로그램에서 수신 프로그램으로 전송되는 방법. (Async Mode, Batch Mode 등)


> #### Point-to-Point Messaging System(PTP: 지점 간 메시징 시스템)

PTP Messaging Model은 메시지가 오직 하나의 Receiver에 의해서만 사용되는 모델로, 명명된 목적지로 메시지를 보낸다. 전형적으로 Receiver Channel을 구독하고 특정 Queue에 전송된 모든 메시지를 수신하지 않고, Sender가 Queue에 전송한 메시지를 Receiver가 요청하는 방식이다.

- 포트를 통해 들어오는 메시지는 대기하는 메시지 큐의 종점이 명명된 목적지가 된다.
- Sender와 ,Receiver가 있으며 큐로 정의도는 목적지를 통해 메시지를 교환한다.
- Sender는 Queue로 전달할 메시지를 생성하고, Receiver는 해당 Qeueue에서 메시지를 사용한다.
- 주로 하나의 메시지 Receiver에 의해 수신된 단일 메시지가 사용되며 여러개의 Sender도 가능하지만 해당 Queue Message를 보내더라도 오직 한 개의 Receiver만 수신하게된다.


>  #### Publish-Subseriber Messaging System(게시-구독 메시징 시스템)

Subscriber(구독자)가 특정 Topic이나 Event에 대해서 어떤 Subscription Interest(구독의사)를 등록하고, 해당 Event에 대해 일련의 통지를 비동기 방식으로 받는다. 구독자는 어떤 이벤트나 이벤트 유형에 대한 구독의 사를 표시할 수 있고, 등록된 구독 의사에 맞는 게시자에 의해 생성된 일련의 이벤트를 통보 받는다.

PTP모델과 차이점은 Receiver가 Topic에서 꺼내지 않고 모든 Receiver에 Broadcast된다. 또한 모든 메시지 구독자(Consumer)는 메시지를 수신할 Topic을 기다린다.

- 메시지는 Topic으로 정의된 채널을 통해 공유
- 메시지는 한 개 이상 또는 그 이상의 Consumer에게 전달된다.
- Publisher(게시자)는 어느 구독자가 Topic 메시지를 받게 되는지 모른다.

> #### AMQP(Advanced Message Queueing Protocol)

비동기 Message Queue 방식의 공개 Protocol로써 Producer, Consumer, Broker/Server 세 가지로 구성된다. Producer는 메시지를 중개하는 역할을 하는 Broker에게 보내면 Broker는 Consumer에게 순서대로 전달한다. 모든 Broker는 Producer의 메시지를 Message Queue로 라우팅 하는 역할을 하는 교환기 역할을 한다.

- 직접교환 : Key를 이용한 라우팅 방식을 사용하여 메시지의 라우팅 키와 동일한 이름의 Queue로 전달한다.
- fan-out 교환 : fan-out 교환 방식은 라우팅 Key가 무시되고 교환 가능한 범위 내에서 모든 Queue에 대해 메시지 경로를 설정한다.
- Topic 교환 : 와일드 카드를 사용하여 일부 연결된 Qeueue로 메시지 경로를  설정하는 방식이다. 일반적으로 메시지 멀티캐스트 라우팅에 사용

> #### Big-Data Streming Application Messaging System

- Big-Data의 계층
  - Ingestion Layer(수집 계층) : 처리 과정에 필요한 입력 데이터는 어떤 저장소 시스템에 수집된다. 동일하거나 다른 처리과정을 완료하기 위한 데이터 소스는 여러 개가 될 수 있다.
  - Processing Layer(처리 계층) : 수집 계층에서 받은 데이터를 처리하는 비지니스 로직으로 사용 가능한 데이터 형식을 만들기 위해 일부 데이터를 변환한다.
  - consumption Layer(소비 계층) : 처리 계층에서의 처리 과정을 거친 데이터들이며, 처리된 데이터는 비지니스 의사 결정에 중요하고 정확한 정보를 가진다.

이처럼 Streming Application은 Processing Layer에 위치하며 동일 데이터를 여러 Application에서 동시에 사용되거나 방법들을 제공한다. 그 중 Streming, Batch, Micro Batch 방식이 될 수도 있다.
